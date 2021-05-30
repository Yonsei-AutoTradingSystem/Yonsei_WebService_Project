############ 변동성 돌파 전략 (래리 윌리엄스) ##########
'''
1) 가격 변동폭 계산: 투자하려는 가상화폐의 전일 고가(high)에서 전일 저가(low)를 빼서 가상화폐의 가격 변동폭을 구합니다.
2) 매수 기준: 당일 시간에서 (변동폭 * 0.5) 이상 상승하면 해당 가격에 바로 매수합니다.
3) 매도 기준: 당일 종가에 매도합니다.
1시간 사이에 5%오르면 매수 다시 1시간 사이에 5%오르면 매도.
'''

import pyupbit
import time
import datetime
import math
import requests

############# 업비트 api auth ############
#with open("Upbit_API_KEY.txt", 'r', encoding='utf-8') as f:
    #lines = f.readlines()
    #access_key = lines[0].strip()
    #secret_key = lines[1].strip()
    #upbit = pyupbit.Upbit(access_key, secret_key)
    #print(upbit.get_balances())

ACCESS_KEY = ""
SECRET_KEY = ""

upbit = pyupbit.Upbit(ACCESS_KEY, SECRET_KEY)
print(upbit.get_balances())
#########################


##########상수 정의#############
GAP_RATE = 0.1
TICKER = ""

######### 함수 정의 ###########
def log(pLog):
    start = "\n==============" + str(datetime.datetime.now()) + "=============\n"
    end = "\n===================================================\n"
    #본인이 로그 기록하고 싶은 경로
    fname = "C:\\Users\\max14\\OneDrive\\바탕 화면\\융합 프로젝트\\trading source\\log_" + str(datetime.datetime.today().strftime('%Y%m%d')) + ".txt"

    f = open(fname, 'a')

    f.write(start + str(pLog) + end)

    f.close()

def get_target_price(ticker, gap_rate):
    #########목표가 계산하기 ##############
    #시간봉 이용하기
    url = "https://api.upbit.com/v1/candles/minutes/60"

    querystring = {"market": ticker,"count":"1"}

    headers = {"Accept": "application/json"}

    response = requests.request("GET", url, headers=headers, params=querystring)

    #print(response.text)
    hour_data = response.json()
    trade_price = hour_data[0]["trade_price"] #종가
    opening_price = hour_data[0]["opening_price"] #시가
    target = opening_price + (trade_price - opening_price) * gap_rate

    '''
    #일일 데이터
    df = pyupbit.get_ohlcv(ticker)
    yesterday = df.iloc[-2]

    today_open = yesterday['close']
    yesterday_high = yesterday['high']
    yesterday_low = yesterday['low']
    target = today_open + (yesterday_high - yesterday_low) * gap_rate #매수기준 가격
    '''
    return target

def buy_crypto_currency(ticker):
    cur_wallet = upbit.get_balances()[0]  # 보유중인 원화 GET - 배열안에 딕셔너리로 들어옴.
    cur_balance = cur_wallet['balance']

    orderbook = pyupbit.get_orderbook(ticker)[0]['orderbook_units']
    sell_price = orderbook[0]['ask_price']  # 최우선 매도 호가
    unit = float(cur_balance) / float(sell_price) #돈 있는만큼 계산
    #unit = float(10000) / float(sell_price) #돈 있는만큼 계산
    ret = upbit.buy_limit_order(ticker, sell_price, unit)

    log("current balance : " + str(cur_balance) + " / buy amount : " + str(sell_price * unit) + "won")
    log("buy result : " + str(ret))

def sell_crypto_currency(ticker, currency):
    for item in upbit.get_balances():
        if item['currency'] == currency:
            unit = float(item['balance']) #해당 코인 개수 (KRW이면 원화)

            ret = upbit.sell_market_order(ticker, unit)

            log("unit : "+ str(unit))
            log("sell result : " + str(ret))
        else:
            log("you don't have " + str(currency))



######################################################

now = datetime.datetime.now()
#판매 기준 시각
#criteria = datetime.datetime(now.year, now.month, now.day) + datetime.timedelta(days=0, hours=9) #다음 날 9시
criteria = datetime.datetime(now.year, now.month, now.day, now.hour) + datetime.timedelta(days=0, hours=0, minutes = 55) #매시 55분


############ 목표가 계산하기 ###############
target_price_BTC = get_target_price("KRW-BTC", GAP_RATE)
#target_price_DOGE = get_target_price("KRW-DOGE", GAP_RATE)

#print("target_price_DOGE : " + str(target_price_DOGE))
print("target_price_BTC : " + str(target_price_BTC))


while True:
    try:
        ########## 단계 1. 자정에 목표가 갱신 및 전량 매도하기 ##########
        now = datetime.datetime.now()
        if criteria < now < criteria + datetime.timedelta(seconds=10):
            #target_price_DOGE = get_target_price("KRW-DOGE")
            target_price_BTC = get_target_price("KRW-BTC")
            #criteria = datetime.datetime(now.year, now.month, now.day) + datetime.timedelta(days=1, hours=9)
            criteria = datetime.datetime(now.year, now.month, now.day, now.hour) + datetime.timedelta(days=0, hours=0, minutes = 55) #매시 55분
            sell_crypto_currency("KRW-BTC", "BTC")
    except:
        log("error occured while selling!!!")

    try:
        ############# 단계2. 주기적으로 현재가 얻어오기 #################
        current_price_BTC = pyupbit.get_current_price("KRW-BTC")
        #current_price_DOGE = pyupbit.get_current_price("KRW-DOGE")
        # log("KRW-BTC : " + str(current_price_BTC) + "원")
        if datetime.datetime.now().second % 30 == 0: #30초에 한번씩 현재 가격로그 기록
            #log("current price KRW-DOGE : " + str(current_price_DOGE) + "원")
            log("current price KRW-BTC : " + str(current_price_BTC) + "원")
    except:
        log("error occured while getting current price!!!")

    try:
        ############## 단계 3. 매수 시도 ###############
        if datetime.datetime.now().second % 30 == 0: #30초에 한번씩 현재 가격로그 기록
            log("current_price_BTC : " + str(current_price_BTC) + " / target_price_BTC : " + str(target_price_BTC))
        if current_price_BTC > target_price_BTC:
            log("!!!!!!!!!!!!!!!buying start!!!!!!!!!!!!!!!!!!!")
            log("current_price_BTC : " + str(current_price_BTC) + " / target_price_BTC : " + str(target_price_BTC))
            buy_crypto_currency("KRW-BTC")
    except:
        log("error occured while buying!!!")

    time.sleep(1)

    #price = pyupbit.get_current_price(["KRW-BTC","KRW-XRP","KRW-DOGE","KRW-ETH"])
    #print(price)

    #df_BTC = pyupbit.get_ohlcv("KRW-BTC")
    #df_XRP = pyupbit.get_ohlcv("KRW-XRP")
    #df_DOGE = pyupbit.get_ohlcv("KRW-DOGE")
    #df_ETH = pyupbit.get_ohlcv("KRW-ETH")