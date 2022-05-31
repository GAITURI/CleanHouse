/*
* Copyright (C) 2014 Vincent Mi
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.cleanhouse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ColorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rounded, container, false)
        val adapter =
            StreamAdapter(activity)
        (view.findViewById<View>(R.id.main_list) as ListView).adapter = adapter
        adapter.add(
            ColorItem(
                android.R.color.darker_gray, "Tufa at night", "Mono Lake, CA",
                ScaleType.CENTER
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.holo_orange_dark, "Starry night", "Lake Powell, AZ",
                ScaleType.CENTER_CROP
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.holo_blue_dark, "Racetrack playa", "Death Valley, CA",
                ScaleType.CENTER_INSIDE
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.holo_green_dark, "Napali coast", "Kauai, HI",
                ScaleType.FIT_CENTER
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.holo_red_dark, "Delicate Arch", "Arches, UT",
                ScaleType.FIT_END
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.holo_purple, "Sierra sunset", "Lone Pine, CA",
                ScaleType.FIT_START
            )
        )
        adapter.add(
            ColorItem(
                android.R.color.white, "Majestic", "Grand Teton, WY",
                ScaleType.FIT_XY
            )
        )
        return view
    }

    internal inner class ColorItem(
        val mResId: Int,
        val mLine1: String,
        val mLine2: String,
        val mScaleType: ScaleType
    )

    internal inner class StreamAdapter(context: Context?) :
        ArrayAdapter<ColorItem?>(context!!, 0) {
        private val mInflater: LayoutInflater
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val view: ViewGroup
            view = if (convertView == null) {
                mInflater.inflate(R.layout.rounded_item, parent, false) as ViewGroup
            } else {
                convertView as ViewGroup
            }
            val item = getItem(position)
            (view.findViewById<View>(R.id.imageView1) as ImageView).setImageResource(
                item!!.mResId
            )
            (view.findViewById<View>(R.id.imageView1) as ImageView).scaleType = item.mScaleType
            (view.findViewById<View>(R.id.textView1) as TextView).text = item.mLine1
            (view.findViewById<View>(R.id.textView2) as TextView).text = item.mLine2
            (view.findViewById<View>(R.id.textView3) as TextView).text = item.mScaleType.toString()
            return view
        }

        init {
            mInflater = LayoutInflater.from(getContext())
        }
    }
}