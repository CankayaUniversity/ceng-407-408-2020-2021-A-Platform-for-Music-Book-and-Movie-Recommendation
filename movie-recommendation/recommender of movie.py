import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
from keras.models import load_model
from keras.layers import Input, Embedding, Flatten, Dot, Dense
from keras.models import Model



model2 = load_model('regression_model2(movies).h5')



"Making Recommendations"



# Creating dataset for making recommendations for the first user

dataset = pd.read_csv('sorted_movies_ratings_new.csv')
movie_data = np.array(list(set(dataset.movie_id)))



user = np.array([332 for i in range(len(movie_data))])




predictions = model2.predict([user, movie_data])


predictions = np.array([a[0] for a in predictions])



recommended_movie_ids = (-predictions).argsort()[:20]




movies = pd.read_csv('new_movie_big.csv')


m=movies[movies['movie_id'].isin(recommended_movie_ids)]




print(m[['title', 'genres']])
