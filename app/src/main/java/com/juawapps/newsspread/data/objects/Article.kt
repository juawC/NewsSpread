package com.juawapps.newsspread.data.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

import java.util.Date
/**
 * source : {"id":"bbc-news","name":"BBC News"}
 * author : BBC News
 * title : Iraq declares war with IS is over
 * description : PM Haider al-Abadi says Iraqi troops are in now complete control of the country.
 * url : http://www.bbc.co.uk/news/world-middle-east-42291985
 * urlToImage : https://ichef-1.bbci.co.uk/news/1024/cpsprodpb/11EED/production/_99135437_043072258-1.jpg
 * publishedAt : 2017-12-09T11:34:27Z
 */
@Entity(tableName = "articles", primaryKeys = arrayOf("author", "title"))
class Article(
        @field:Embedded var source: ArticleSource?,
        var author: String,
        var title: String,
        var description: String?,
        var url: String?,
        @field:ColumnInfo(name = "url_to_image") var urlToImage: String?,
        @field:ColumnInfo(name = "published_at") var publishedAt: Date?,
        var category: String?)
