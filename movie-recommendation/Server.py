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

        model2 = load_model('regression_model2(movies).h5')

        dataset = pd.read_csv('sorted_movies_ratings_new.csv')
        movie_data = np.array(list(set(dataset.movie_id)))

        user = np.array([userId for i in range(len(movie_data))])

        predictions = model2.predict([user, movie_data])

        predictions = np.array([a[0] for a in predictions])

        recommended_movie_ids = (-predictions).argsort()[:20]

        movies = pd.read_csv('new_movie_big.csv')

        m = movies[movies['movie_id'].isin(recommended_movie_ids)]

        m = m["movie_id"]

        s = m.to_string(index=False)

        print(s)





        self.wfile.write(bytes("<p> %s </p>" % s, "utf-8"))

"""
        self.wfile.write(bytes("<html>", "utf-8"))
        self.wfile.write(bytes("<p>Request: %s</p>" % self.path, "utf-8"))
        self.wfile.write(bytes("<body>", "utf-8"))
        self.wfile.write(bytes("<p> %s </p>" % result, "utf-8"))
        self.wfile.write(bytes("</body></html>", "utf-8"))
"""
if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")