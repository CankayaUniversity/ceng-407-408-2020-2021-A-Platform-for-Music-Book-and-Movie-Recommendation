import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
import warnings

from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model

dataset = pd.read_csv('music_ratings_new.csv')


from sklearn.model_selection import train_test_split
train, test = train_test_split(dataset, test_size=0.2, random_state=42)


n_users = len(dataset.userid.unique())
n_users = n_users+500

n_music = len(dataset.music_id.unique())
n_music



"Creating Neural Network"

from keras.layers import Concatenate

# creating book embedding path
music_input = Input(shape=[1], name="Music-Input")
music_embedding = Embedding(n_music+1, 5, name="Music-Embedding")(music_input)
music_vec = Flatten(name="Flatten-Music")(music_embedding)

# creating user embedding path
user_input = Input(shape=[1], name="User-Input")
user_embedding = Embedding(n_users+1, 5, name="User-Embedding")(user_input)
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
from keras.models import load_model

if os.path.exists('regression_model2(music).h5'):
    model2 = load_model('regression_model2(music).h5')
else:
    history = model2.fit([train.userid, train.music_id], train.rating, epochs=40, verbose=1)
    model2.save('regression_model2(music).h5')
    plt.plot(history.history['loss'])
    plt.xlabel("Epochs")
    plt.ylabel("Training Error")



"Making Recommendations"

music_data = np.array(list(set(dataset.music_id)))



user = np.array([2998 for i in range(len(music_data))])




predictions = model2.predict([user, music_data])

predictions = np.array([a[0] for a in predictions])

recommended_music_ids = (-predictions).argsort()[:20]



music = pd.read_csv('top10s.csv')


m=music[music['id'].isin(recommended_music_ids)]

print(m[['title','top genre', 'artist']])