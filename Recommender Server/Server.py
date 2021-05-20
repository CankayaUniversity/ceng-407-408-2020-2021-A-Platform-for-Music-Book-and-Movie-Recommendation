from http.server import BaseHTTPRequestHandler, HTTPServer
import time
import pandas as pd
import matplotlib.pyplot as plt
import os
import warnings
from keras.models import load_model
import numpy as np

import tensorflow

from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model

hostName = "localhost"
serverPort = 5000

class MyServer(BaseHTTPRequestHandler):

    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()

        userId = int(self.requestline[11])

        if self.requestline[4:10] == "/movie":
            model2 = load_model('src/models/regression_model2_movies.h5')

            dataset = pd.read_csv('src/csv/movie_ratings.csv')
            movie_data = np.array(list(set(dataset.movie_id)))

            user = np.array([userId for i in range(len(movie_data))])

            predictions = model2.predict([user, movie_data])
            predictions = np.array([a[0] for a in predictions])

            recommended_movie_ids = (-predictions).argsort()[:20]

            movies = pd.read_csv('src/csv/movie.csv')

            m = movies[movies['id'].isin(recommended_movie_ids)]
            result = m["id"]

            self.wfile.write(bytes("%s" % result.to_string(index=False), "utf-8"))

        elif self.requestline[4:10] == "/books":
            model2 = load_model('src/models/regression_model2_book.h5')

            dataset = pd.read_csv('src/csv/book_rating.csv')
            book_data = np.array(list(set(dataset.book_id)))

            user = np.array([userId for i in range(len(book_data))])

            predictions = model2.predict([user, book_data])
            predictions = np.array([a[0] for a in predictions])

            recommended_book_ids = (-predictions).argsort()[:20]

            books = pd.read_csv('src/csv/book.csv')

            b = books[books['id'].isin(recommended_book_ids)]
            result = b[['id']]

            self.wfile.write(bytes("%s" % result.to_string(index=False), "utf-8"))

        elif self.requestline[4:10] == "/music":
            model2 = load_model('src/models/regression_model2_music.h5')

            dataset = pd.read_csv('src/csv/music_rating.csv')
            music_data = np.array(list(set(dataset.music_id)))

            user = np.array([userId for i in range(len(music_data))])

            predictions = model2.predict([user, music_data])
            predictions = np.array([a[0] for a in predictions])

            recommended_music_ids = (-predictions).argsort()[:20]

            music = pd.read_csv('src/csv/music.csv')

            m = music[music['id'].isin(recommended_music_ids)]

            result = m["id"]

            self.wfile.write(bytes("%s" % result.to_string(index=False), "utf-8"))

if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")
