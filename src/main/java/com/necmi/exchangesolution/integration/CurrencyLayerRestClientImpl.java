package com.necmi.exchangesolution.integration;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.necmi.exchangesolution.controllers.ConversionResponse;
import com.necmi.exchangesolution.integration.dto.ExchangeRateResponse;

@Component
public class CurrencyLayerRestClientImpl implements CurrencyLayerRestClient {

    public static final String ACCESS_KEY = "4073dab30e553070481b44795255d7c7";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String LIVE_ENDPOINT = "live";
    public static final String CONVERT_ENDPOINT = "convert";
    
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    
	@Override
	/**
	 * Exchange Rate API
	 */
	public ExchangeRateResponse getExchangeRate(String currency1, String currency2) {
		final String currencies = currency1 + "," + currency2;
		HttpGet get = new HttpGet(BASE_URL + LIVE_ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" + currencies);
		
		ExchangeRateResponse response = new ExchangeRateResponse();
		try {
			CloseableHttpResponse httpResponse =  httpClient.execute(get);
			HttpEntity entity = httpResponse.getEntity();
			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
			
			if (exchangeRates.getBoolean("success")) {
				response.setSuccess(true);
				BigDecimal rate = exchangeRates.getJSONObject("quotes").getBigDecimal(currency1+currency2);
				response.setRate(rate);
				response.setSourceCurrency(exchangeRates.getString("source"));
				response.setTargetCurrency(currency1+currency2);
			} else {
				response.setSuccess(false);
				response.setErrorCode(exchangeRates.getJSONObject("error").getInt("code"));
				response.setErrorInfo(exchangeRates.getJSONObject("error").getString("info"));
			}
			
			httpResponse.close();
			httpClient.close();
			
		} catch (ClientProtocolException e) {
			response.setSuccess(false);
			response.setErrorCode(1001);
			response.setErrorInfo("An error occured while getting exchange rate");
		} catch (IOException e) {
			response.setSuccess(false);
			response.setErrorCode(1002);
			response.setErrorInfo("An error occured while getting exchange rate");
		} catch (ParseException e) {
			response.setSuccess(false);
			response.setErrorCode(1003);
			response.setErrorInfo("An error occured while getting exchange rate");
		} catch (JSONException e) {
			response.setSuccess(false);
			response.setErrorCode(1004);
			response.setErrorInfo("An error occured while getting exchange rate");
		}
		return response;
	}
	
	/**
	 * Conversion API
	 */
	public ConversionResponse convertAmount(BigDecimal sourceAmount, String sourceCurrency, String targetCurrency) {

		ConversionResponse response = new ConversionResponse();
		ExchangeRateResponse rateResponse = getExchangeRate(sourceCurrency, targetCurrency);
		if (rateResponse.isSuccess()) {
			response.setSuccess(true);
			BigDecimal conversionResult = sourceAmount.multiply(rateResponse.getRate());
			response.setTargetAmount(conversionResult);
		} else {
			response.setSuccess(false);
			response.setErrorCode(rateResponse.getErrorCode());
			response.setErrorInfo(rateResponse.getErrorInfo());
		}
		return response;
		
		/*
		 * apilayer da convert API'si ücretli oldugundan asagidaki kisim commentlendi. Yerine exchangeRate API si cagrilarak manuel hesaplama yapildi.
		 * 
		HttpGet get = new HttpGet(BASE_URL + CONVERT_ENDPOINT + "?access_key=" + ACCESS_KEY + "&from=" + sourceCurrency + "&to=" + targetCurrency + "&amount=" + sourceAmount);
		ConversionResponse response = new ConversionResponse();
		try {
			CloseableHttpResponse httpResponse =  httpClient.execute(get);
			HttpEntity entity = httpResponse.getEntity();
			JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity));
			
			if (jsonObject.getBoolean("success")) {
				response.setSuccess(true);
				BigDecimal conversionResult = jsonObject.getBigDecimal("result");
				response.setTargetAmount(conversionResult);
			} else {
				response.setSuccess(false);
				response.setErrorCode(jsonObject.getJSONObject("error").getInt("code"));
				response.setErrorInfo(jsonObject.getJSONObject("error").getString("info"));
			}
			
			httpResponse.close();
			httpClient.close();
		} catch (ClientProtocolException e) {
			response.setSuccess(false);
			response.setErrorCode(1011);
			response.setErrorInfo("An error occured while converting the currency to target currency");
		} catch (IOException e) {
			response.setSuccess(false);
			response.setErrorCode(1012);
			response.setErrorInfo("An error occured while converting the currency to target currency");
		} catch (ParseException e) {
			response.setSuccess(false);
			response.setErrorCode(1013);
			response.setErrorInfo("An error occured while converting the currency to target currency");
		} catch (JSONException e) {
			response.setSuccess(false);
			response.setErrorCode(1014);
			response.setErrorInfo("An error occured while converting the currency to target currency");
		}
		return response;
		*/
	}

}
