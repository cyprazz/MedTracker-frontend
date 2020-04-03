package com.example.medtracker

class HomeFeed (val videos: List<Video>)

class Video (val id: Int, val name: String, val link: String, val imageUrl: String, val numberOfViews: Int)
