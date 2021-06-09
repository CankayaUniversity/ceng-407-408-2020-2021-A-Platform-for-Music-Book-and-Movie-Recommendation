from http.server import BaseHTTPRequestHandler, HTTPServer

import pandas as pd
import psycopg2

hostName = "localhost"
serverPort = 5001


class MyServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()

    con = psycopg2.connect(
        host="localhost",
        database="quirec",
        user="postgres",
        password="Ps6225950"
    )

    sql_query = pd.read_sql_query('''select * from quirec.music''', con)
    df = pd.DataFrame(sql_query)
    df.to_csv('C:/Datasets/music.csv', index=False)

    sql_query = pd.read_sql_query('''select * from quirec.movie''', con)
    df = pd.DataFrame(sql_query)
    df.to_csv('C:/Datasets/movie.csv', index=False)

    sql_query = pd.read_sql_query('''select * from quirec.book''', con)
    df = pd.DataFrame(sql_query)
    df.to_csv('C:/Datasets/book.csv', index=False)


if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")