# Python 3 server example
from http.server import BaseHTTPRequestHandler, HTTPServer
import time
import pandas as pd
import matplotlib.pyplot as plt
import os
import warnings
from keras.models import load_model
import numpy as np

from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model

hostName = "localhost"
serverPort = 5000

class MyServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()


        userId=int(self.path[1:])

        model2 = load_model('regression_model2.h5')

        dataset = pd.read_csv('ratings_sorted.csv')
        book_data = np.array(list(set(dataset.book_id)))

        user = np.array([userId for i in range(len(book_data))])

        predictions = model2.predict([user, book_data])

        predictions = np.array([a[0] for a in predictions])

        recommended_book_ids = (-predictions).argsort()[:20]

        books = pd.read_csv('books.csv')

        b = books[books['id'].isin(recommended_book_ids)]



        result = b[['id']]

        s = result.to_string(index=False)

        print(s)


        self.wfile.write(bytes("<p> %s </p>" % s, "utf-8"))


if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")