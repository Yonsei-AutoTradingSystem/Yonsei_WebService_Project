import requests
import pyupbit

unit = "60"

url = "https://api.upbit.com/v1/candles/minutes/" + unit

querystring = {"market":"KRW-BTC","count":"1"}

response = requests.request("GET", url, params=querystring)

print(response.text)


hour_data = response.json()
print("high price : " + str(hour_data[0]["high_price"]))

#hour_high = response[0]["high_price"].text

#print(hour_high)
