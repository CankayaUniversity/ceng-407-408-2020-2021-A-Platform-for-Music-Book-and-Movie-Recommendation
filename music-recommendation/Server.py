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
        self.send_header("Content-type", "text/html")
        self.end_headers()

        dataset = pd.read_csv('music_ratings_new.csv')
        model2 = load_model('regression_model2(music).h5')
        userId=int(self.path[1:])

        music_data = np.array(list(set(dataset.music_id)))

        user = np.array([userId for i in range(len(music_data))])

        predictions = model2.predict([user, music_data])

        predictions = np.array([a[0] for a in predictions])

        recommended_music_ids = (-predictions).argsort()[:20]

        music = pd.read_csv('top10s.csv')

        m = music[music['id'].isin(recommended_music_ids)]

        print(m[['title', 'top genre', 'artist']])

        result = m[['title', 'top genre', 'artist']]

        result2=m[['id']]

        result = result.to_json()





        self.wfile.write(bytes("<html>", "utf-8"))
        self.wfile.write(bytes("<p>Request: %s</p>" % self.path, "utf-8"))
        self.wfile.write(bytes("<body>", "utf-8"))
        self.wfile.write(bytes("<p> %s </p>"% result, "utf-8"))
        self.wfile.write(bytes("<p> %s </p>" % result2, "utf-8"))
        self.wfile.write(bytes("</body></html>", "utf-8"))

if __name__ == "__main__":        
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")