package com.example.medtracker

class DrugFeed (val data: List<drugdata>)

class drugdata (val id: Int, val type: String, val attributes: attributes)

class attributes (val name: String, val thumbnailURL: String)
