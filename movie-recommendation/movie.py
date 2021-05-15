import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
import warnings

from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model

dataset = pd.read_csv('sorted_movies_ratings_new.csv')




from sklearn.model_selection import train_test_split
train, test = train_test_split(dataset, test_size=0.2, random_state=42)



n_users = len(dataset.user_id.unique())
n_users = n_users + 500


n_movies = len(dataset.movie_id.unique())
n_movies



"Creating Neural Network"

from keras.layers import Concatenate

# creating book embedding path
movie_input = Input(shape=[1], name="Movie-Input")
movie_embedding = Embedding(n_movies+1, 5, name="Movie-Embedding")(movie_input)
movie_vec = Flatten(name="Flatten-Movies")(movie_embedding)

# creating user embedding path
user_input = Input(shape=[1], name="User-Input")
user_embedding = Embedding(n_users+1, 5, name="User-Embedding")(user_input)
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
from keras.models import load_model



if os.path.exists('regression_model2(movies).h5'):
    model2 = load_model('regression_model2(movies).h5')
else:
    history = model2.fit([train.user_id,train.movie_id], train.rating, epochs=25, verbose=1)
    model2.save('regression_model2(movies).h5')
    plt.plot(history.history['loss'])
    plt.xlabel("Epochs")
    plt.ylabel("Training Error")



"Making Recommendations"
# Creating dataset for making recommendations for the first user
movie_data = np.array(list(set(dataset.movie_id)))



user = np.array([1999 for i in range(len(movie_data))])




predictions = model2.predict([user, movie_data])


predictions = np.array([a[0] for a in predictions])



recommended_movie_ids = (-predictions).argsort()[:20]




movies = pd.read_csv('new_movie_big.csv')


m=movies[movies['id'].isin(recommended_movie_ids)]




print(m[['title', 'genres']])
