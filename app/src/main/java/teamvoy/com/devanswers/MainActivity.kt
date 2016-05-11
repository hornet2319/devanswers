package teamvoy.com.devanswers

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MainActivity : AppCompatActivity() {
    val baseURL: String = "http://developerexcuses.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        root.setOnClickListener({ fetch() })
    }

    fun fetch() {
        baseURL.httpGet().responseString { request, response, result ->
            Log.i("RESULT",(result as Result).get())
            when (result){
                is Result.Success ->{
                    val doc:Document = Jsoup.parse(result.value)
                    val elem: String? = doc.select("a").first().text()
                    text.text = elem
                }
                is Result.Failure ->{

                }
            }
        }
    }
}


