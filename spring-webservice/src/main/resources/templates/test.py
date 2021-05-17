import cgi

form = cgi.FieldStorage()

first_name = form.getvalue('first_name').capitalize()
last_name  = form.getvalue('last_name').capitalize()

print ("Content-type:text/html\r\n\r\n")
print ("<html>")
print ("<head>")
print ("<title>Hello - Second CGI Program</title>")
print ("</head>")
print ("<body>")
print ("<h2>Your name is {}. {} {}</h2>".format(last_name, first_name, last_name))
print ("</body>")
print ("</html>")