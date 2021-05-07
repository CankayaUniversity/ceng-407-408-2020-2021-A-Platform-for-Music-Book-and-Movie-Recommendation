import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
import warnings
from keras.models import load_model

from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model

dataset = pd.read_csv('music_ratings_new.csv')

model2 = load_model('regression_model2(music).h5')

music_data = np.array(list(set(dataset.music_id)))



user = np.array([5 for i in range(len(music_data))])




predictions = model2.predict([user, music_data])

predictions = np.array([a[0] for a in predictions])

recommended_music_ids = (-predictions).argsort()[:20]



music = pd.read_csv('top10s.csv')


m=music[music['id'].isin(recommended_music_ids)]

print(m[['title','top genre', 'artist']])

result=str(m[['title','top genre', 'artist']])

print(result)

