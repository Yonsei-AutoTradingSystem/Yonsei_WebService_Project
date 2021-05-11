import pymysql

conn = pymysql.connect(
    host = "",
    user = "admin",
    password = "",
    db = "webservice",
    charset = "utf8"
)

try:
    curs = conn.cursor(pymysql.cursors.DictCursor)
    sql = "select * from user_test"
    curs.execute(sql)
    rows = curs.fetchall()

    for row in rows:
        print(row)
finally:
    conn.close()
