from http.server import BaseHTTPRequestHandler, HTTPServer

import matplotlib.pyplot as plt
import pandas as pd
from keras.layers import Input, Embedding, Flatten, Dense
from keras.models import Model

hostName = "localhost"
serverPort = 5002


class MyServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()

        dataset = pd.read_csv('C:/Datasets/music_ratings.csv')

        from sklearn.model_selection import train_test_split
        train, test = train_test_split(dataset, test_size=0.2, random_state=42)

        n_users = len(dataset.user_id.unique())
        n_users = n_users + 500

        n_music = len(dataset.music_id.unique())
        n_music

        from keras.layers import Concatenate
        # creating music embedding path
        music_input = Input(shape=[1], name="Music-Input")
        music_embedding = Embedding(n_music + 1, 5, name="Music-Embedding")(music_input)
        music_vec = Flatten(name="n_music-Music")(music_embedding)

        # creating user embedding path
        user_input = Input(shape=[1], name="User-Input")
        user_embedding = Embedding(n_users + 1, 5, name="User-Embedding")(user_input)
        user_vec = Flatten(name="Flatten-Users")(user_embedding)

        # concatenate features
        conc = Concatenate()([music_vec, user_vec])

        # add fully-connected-layers
        fc1 = Dense(128, activation='relu')(conc)
        fc2 = Dense(32, activation='relu')(fc1)
        out = Dense(1)(fc2)

        # Create model and compile it
        model2 = Model([user_input, music_input], out)
        model2.compile('adam', 'mean_squared_error')

        history = model2.fit([train.user_id, train.music_id], train.rating, epochs=25, verbose=1)
        model2.save('regression_model2_music.h5')
        plt.plot(history.history['loss'])
        plt.xlabel("Epochs")
        plt.ylabel("Training Error")

        dataset = pd.read_csv('C:/Datasets/book_ratings.csv')

        train, test = train_test_split(dataset, test_size=0.2, random_state=42)

        n_users = len(dataset.user_id.unique())
        n_users = n_users + 500

        n_books = len(dataset.book_id.unique())
        n_books

        from keras.layers import Concatenate

        # creating book embedding path
        book_input = Input(shape=[1], name="Book-Input")
        book_embedding = Embedding(n_books + 1, 5, name="Book-Embedding")(book_input)
        book_vec = Flatten(name="Flatten-Movies")(book_embedding)

        # creating user embedding path
        user_input = Input(shape=[1], name="User-Input")
        user_embedding = Embedding(n_users + 1, 5, name="User-Embedding")(user_input)
        user_vec = Flatten(name="Flatten-Users")(user_embedding)

        # concatenate features
        conc = Concatenate()([book_vec, user_vec])

        # add fully-connected-layers
        fc1 = Dense(128, activation='relu')(conc)
        fc2 = Dense(32, activation='relu')(fc1)
        out = Dense(1)(fc2)

        # Create model and compile it
        model2 = Model([user_input, book_input], out)
        model2.compile('adam', 'mean_squared_error')

        history = model2.fit([train.user_id, train.book_id], train.rating, epochs=25, verbose=1)
        model2.save('regression_model2_book.h5')
        plt.plot(history.history['loss'])
        plt.xlabel("Epochs")
        plt.ylabel("Training Error")

        dataset = pd.read_csv('C:/Datasets/movie_ratings.csv')

        train, test = train_test_split(dataset, test_size=0.2, random_state=42)

        n_users = len(dataset.user_id.unique())
        n_users = n_users + 500

        n_movies = len(dataset.movie_id.unique())
        n_movies

        from keras.layers import Concatenate

        # creating movie embedding path
        movie_input = Input(shape=[1], name="Movie-Input")
        movie_embedding = Embedding(n_movies + 1, 5, name="Movie-Embedding")(movie_input)
        movie_vec = Flatten(name="Flatten-Movies")(movie_embedding)

        # creating user embedding path
        user_input = Input(shape=[1], name="User-Input")
        user_embedding = Embedding(n_users + 1, 5, name="User-Embedding")(user_input)
        user_vec = Flatten(name="Flatten-Users")(user_embedding)

        # concatenate features
        conc = Concatenate()([movie_vec, user_vec])

        # add fully-connected-layers
        fc1 = Dense(128, activation='relu')(conc)
        fc2 = Dense(32, activation='relu')(fc1)
        out = Dense(1)(fc2)

        # Create model and compile it
        model2 = Model([user_input, movie_input], out)
        model2.compile('adam', 'mean_squared_error')

        history = model2.fit([train.user_id, train.movie_id], train.rating, epochs=25, verbose=1)
        model2.save('regression_model2_movies.h5')
        plt.plot(history.history['loss'])
        plt.xlabel("Epochs")
        plt.ylabel("Training Error")


if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")
