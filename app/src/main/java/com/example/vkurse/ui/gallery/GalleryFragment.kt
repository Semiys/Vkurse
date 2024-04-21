package com.example.vkurse.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.vkurse.databinding.FragmentGalleryBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import androidx.appcompat.app.AlertDialog
import com.example.vkurse.R
import com.example.vkurse.ui.UniversityInfoDialogFragment
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    // Добавляем переменную для MapView
    private lateinit var mapView: MapView
    private val universities = listOf(
        University("МГУ", Point(55.702868, 37.528670), "Московский Государственный Университет"),
        // Добавьте другие вузы здесь
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Инициализируем MapView
        mapView = binding.mapview
        mapView.map.move(
            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f)
        )

        return root
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем метки на карту
        universities.forEach { university ->
            val placemark = mapView.map.mapObjects.addPlacemark(university.coordinate)
            // Установите свою иконку для метки, если нужно
            // placemark.setIcon(ImageProvider.fromResource(context, R.drawable.my_icon))

            // Добавляем слушатель нажатий на метку
            placemark.addTapListener { mapObject, point ->
                // Здесь вы можете отобразить информацию о вузе, например, в виде всплывающего окна
                showUniversityInfo(university)
                true
            }
        }
    }
    private fun showUniversityInfo(university: University) {
        UniversityInfoDialogFragment.newInstance(university).show(parentFragmentManager, "university_info")
    }
}
data class University(
    val name: String,
    val coordinate: Point,
    val info: String // Это информация, которая будет отображаться при нажатии
)