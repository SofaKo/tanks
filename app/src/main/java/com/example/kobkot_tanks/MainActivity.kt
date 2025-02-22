package com.example.kobkot_tanks


import androidx.appcompat.app.AppCompatActivity
import android.os. Bundle
import android.view.KeyEvent
import android.view. KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view. KeyEvent.KEYCODE_DPAD_UP
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.FrameLayout
import androidx.core.view.marginTop
import com. example. kobkot_tanks.Direction. UP
import com. example.kobkot_tanks.Direction.DOWN
import com. example.kobkot_tanks.Direction. LEFT
import com. example.kobkot_tanks.Direction.RIGHT
import com.example.kobkot_tanks.databinding.ActivityMainBinding

const val CELL_SIZE = 50

lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var editMode = false
    private val gridDrawer by lazy {
        GridDrawer(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Menu"
    }

    private fun swichEditMode(){
        if (editMode) {
            gridDrawer.removeGrid()
            binding.materialsContainer.visibility = INVISIBLE
        }
        else {
            gridDrawer.drawGrid()
            binding.materialsContainer.visibility = VISIBLE
        }
        editMode = !editMode
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                swichEditMode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_DPAD_UP -> move(UP)
            KEYCODE_DPAD_DOWN -> move(DOWN)
            KEYCODE_DPAD_LEFT -> move(LEFT)
            KEYCODE_DPAD_RIGHT -> move(RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction) {
        when (direction) {
            UP -> {
                binding.myTank.rotation = 0F
                if (binding.myTank.marginTop > 0) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE
                }
            }
            DOWN -> {
                if (binding.myTank.marginTop + binding.myTank.height < binding.container.height / (CELL_SIZE * CELL_SIZE)) {
                    binding.myTank.rotation = 180F
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }
            LEFT -> {
                if (binding.myTank.marginTop > 0) {
                    binding.myTank.rotation = 270F
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
                }
            }
            RIGHT -> {
                if (binding.myTank.marginTop + binding.myTank.height < binding.container.height / (CELL_SIZE * CELL_SIZE)) {
                    binding.myTank.rotation = 90F
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
                }
            }
        }

        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}