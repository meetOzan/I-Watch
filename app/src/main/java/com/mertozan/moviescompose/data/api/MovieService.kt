package com.mertozan.moviescompose.data.api

// Service olarak mı Api olarak mı isimlendirmem lazım ?
interface MovieService {

    // GET https://api.themoviedb.org/3/movie/{movie_id} | Single Movie Details
    // GET https://api.themoviedb.org/3/tv/{series_id} | Single TV Series Details
    // GET https://api.themoviedb.org/3/trending/movie/{time_window} | Trend Movies
    // GET https://api.themoviedb.org/3/trending/tv/{time_window} | Trend TV Series
    // GET https://api.themoviedb.org/3/tv/popular | Popular TV Series
    // GET https://api.themoviedb.org/3/movie/popular | Popular Movies
    // GET https://api.themoviedb.org/3/movie/{movie_id}/images | Get Movie Image

    // GET https://api.themoviedb.org/3/account/{account_id} | Get Account
    // ? -> Retrofitte user mantığı kullanmadım

    // POST https://api.themoviedb.org/3/account/{account_id}/favorite | Add to Fav
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/movies | Get Fav Movies
    // GET https://api.themoviedb.org/3/account/{account_id}/favorite/tv | Get Fav TV Series

}