package com.example.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.databinding.ActivityMoviesListBinding

class MoviesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesListBinding
    private val adapter = MovieListAdapter(this)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarTitle.text = "Фильмы"
        binding.listView.adapter = adapter
        binding.listView.addItemDecoration(MovieCardDecoration(resources))
        binding.listView.layoutManager = GridLayoutManager(this, 2)
        adapter.movieList = getMovies()
        adapter.notifyDataSetChanged()
    }
}

fun getMovies(): List<MovieItem> {
    return listOf(
        MovieItem(
            title = "Inception",
            rate = 8.8,
            description = "A skilled thief with the rare ability to 'extract' information from people's minds takes on the inverse task of 'inception', the implantation of another person's idea into a target's subconscious. Cobb assembles a team for the seemingly impossible task, but a dangerous enemy seems to predict their every move.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=02448fcc6a41491fb5e3f9200cad3e10_l-12928152-images-thumbs&n=13",
        ),
        MovieItem(
            title = "The Shawshank Redemption",
            rate = 9.3,
            description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency. Based on the novella by Stephen King, this film follows Andy Dufresne, a banker wrongly convicted of double murder, who forms an unlikely friendship with Red, a fellow inmate.",
            imageUrl = "https://avatars.dzeninfra.ru/get-zen_doc/4913353/pub_6404de1e976ffe173b74016f_6404df7a36fc3309ced48b34/scale_1200"
        ),
        MovieItem(
            title = "Pulp Fiction",
            rate = 8.9,
            description = "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption. Quentin Tarantino's groundbreaking film revolutionized cinema with its non-linear storytelling and unforgettable characters.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=caab15a2c2692473fa89d99c8b53181c06b868632b0d2dbb-5717770-images-thumbs&n=13"
        ),
        MovieItem(
            title = "The Dark Knight",
            rate = 9.0,
            description = "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice. Heath Ledger's portrayal of the Joker is considered one of the greatest performances in cinema history.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=3a5b571c85cce04081a16574f9fee073_l-5234651-images-thumbs&n=13"
        ),
        MovieItem(
            title = "Schindler's List",
            rate = 8.9,
            description = "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis. This powerful, heart-wrenching film tells the true story of one man who made a difference during humanity's darkest hour.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=2a35cd49dac1d5079863b3b23479ce87_l-9289427-images-thumbs&n=13"
        ),
        MovieItem(
            title = "Forrest Gump",
            rate = 8.8,
            description = "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart. Tom Hanks delivers an unforgettable performance in this heartwarming tale.",
            imageUrl = "https://sun9-13.userapi.com/impg/xDGGmv_gn-fUpojX8RWCE1F94Rqw_7gls3hmJg/fd64gPl376A.jpg?size=632x807&quality=95&sign=82e71031e766926af6fb3d1c98221381&c_uniq_tag=JVOCX-tznNoFvItaz5UqmmVlcXYDHZdWmllz6R5ludA&type=album"
        ),
        MovieItem(
            title = "The Matrix",
            rate = 8.7,
            description = "A computer programmer discovers that reality as he knows it is a simulation created by machines to subdue the human population. He joins a rebellion to overthrow the machines and free humanity. This mind-bending sci-fi classic revolutionized special effects and explored deep philosophical questions.",
            imageUrl = "https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_65ede16e10c9542b729aa64f_65ffb0b474b4ea5e87f2b04e/scale_1200"
        ),
        MovieItem(
            title = "Goodfellas",
            rate = 8.7,
            description = "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate. Martin Scorsese's gritty, fast-paced gangster epic is considered one of the greatest films of all time.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=6d74f82944eefb7bf987b9a04d02fa08_l-3737438-images-thumbs&n=13"
        ),
        MovieItem(
            title = "The Silence of the Lambs",
            rate = 8.6,
            description = "A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims. This psychological thriller features unforgettable performances by Jodie Foster and Anthony Hopkins.",
            imageUrl = "https://i.pinimg.com/736x/ed/c4/f6/edc4f605c66ec3b6443a48ef76920b44.jpg"
        ),
        MovieItem(
            title = "Interstellar",
            rate = 8.6,
            description = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival. Christopher Nolan's epic sci-fi adventure explores themes of love, sacrifice, and the nature of time itself, set against the backdrop of a dying Earth and the search for a new home for humanity.",
            imageUrl = "https://avatars.mds.yandex.net/i?id=aee3054be035e438f7dbf075ecfe1498_l-5337815-images-thumbs&n=13"
        )
    )
}