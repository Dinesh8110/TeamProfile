package com.example.layoutexplorer

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teamportfolio)

        val textview1: TextView = findViewById(R.id.teamMember1Name)

        textview1.setOnClickListener { view ->
            showPopupMenu(view)
        }

        val textview2: TextView = findViewById(R.id.teamMember2Name)

        textview2.setOnClickListener { view ->
            showPopupMenu(view)
        }

        val textview3: TextView = findViewById(R.id.teamMember3Name)

        textview3.setOnClickListener { view ->
            showPopupMenu(view)
        }

        val teamMember1Image: ImageView = findViewById(R.id.teamMember1)
        teamMember1Image.setOnClickListener {
            // Navigate to DineshProfileActivity
            val intent1 = Intent(this, DineshProfileActivity::class.java)
            startActivity(intent1)
        }

        val teamMember2Image: ImageView = findViewById(R.id.teamMember2)
        teamMember2Image.setOnClickListener {
            // Navigate to DineshProfileActivity
            val intent2 = Intent(this, AmirProfileActivity::class.java)
            startActivity(intent2)
        }

        val teamMember3Image: ImageView = findViewById(R.id.teamMember3)
        teamMember3Image.setOnClickListener {
            // Navigate to DineshProfileActivity
            val intent3 = Intent(this, KishoreProfileActivity::class.java)
            startActivity(intent3)
        }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_edit -> {
                    Toast.makeText(this, "Edit selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_delete -> {
                    Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_share -> {
                    Toast.makeText(this, "Share selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionsmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                val homeintent = Intent(this, MainActivity::class.java)
                startActivity(homeintent)
//                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_about -> {
                val aboutintent = Intent(this, AboutUsActivity::class.java)
                startActivity(aboutintent)
               // Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_help -> {
                val helpintent = Intent(this, HelpActivity::class.java)
                startActivity(helpintent)
               // Toast.makeText(this, "help selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}




class DineshProfileActivity : AppCompatActivity() {

    private lateinit var ContextMenu1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dineshprofile)

        ContextMenu1 = findViewById(R.id.profileName1)
        registerForContextMenu(ContextMenu1)

        val contactMeButton1: Button = findViewById(R.id.contactButton1)
        contactMeButton1.setOnClickListener {
            // Replace with your desired phone number
            val phoneNumber: TextView = findViewById(R.id.contactPhone1)
            val phtext = phoneNumber.text.toString().trim();

            // Create an Intent to dial the number
            if (phtext.isNotEmpty()) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val callIntent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:$phtext")
                    }
                    startActivity(callIntent)
                } else {
                    // Request permission
                    requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 101)
                }
            }
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit1 -> {
                Toast.makeText(this, "Context: Edit selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share1 -> {
                val phoneNumber = "916383604191" // WhatsApp requires country code (91 for India)
                val name: TextView = findViewById(R.id.profileName1)
                val s1: TextView = findViewById(R.id.skill1)
                val s2: TextView = findViewById(R.id.skill2)

                val message = "${name.text} Technical Skills: ${s1.text}, ${s2.text}"

                sendWhatsAppMessage(phoneNumber, message)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun sendWhatsAppMessage(phoneNumber: String, message: String) {
        try {
            val formattedNumber = phoneNumber.replace("+", "").replace(" ", "") // Ensure no spaces or "+"
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
                `package` = "com.whatsapp" // Explicitly target WhatsApp
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Send message via"))
            } else {
                Toast.makeText(this, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error sending message.", Toast.LENGTH_SHORT).show()
        }
    }



}

class AmirProfileActivity : AppCompatActivity() {
    private lateinit var ContextMenu2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.amirprofile)

        ContextMenu2 = findViewById(R.id.profileName2)
        registerForContextMenu(ContextMenu2)

        val contactMeButton2: Button = findViewById(R.id.contactButton2)
        contactMeButton2.setOnClickListener {
            // Replace with your desired phone number
            val phoneNumber: TextView = findViewById(R.id.contactPhone2)
            val phtext = phoneNumber.text.toString().trim();

            // Create an Intent to dial the number
            if (phtext.isNotEmpty()) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val callIntent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:$phtext")
                    }
                    startActivity(callIntent)
                } else {
                    // Request permission
                    requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 101)
                }
            }
        }

    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit1 -> {
                Toast.makeText(this, "Context: Edit selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share1 -> {
                Toast.makeText(this, "Context: Share selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}

class KishoreProfileActivity : AppCompatActivity() {
    private lateinit var ContextMenu3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kishoreprofile)

        ContextMenu3 = findViewById(R.id.profileName3)
        registerForContextMenu(ContextMenu3)

        val contactMeButton3: Button = findViewById(R.id.contactButton3)
        contactMeButton3.setOnClickListener {
            // Replace with your desired phone number
            val phoneNumber: TextView = findViewById(R.id.contactPhone3)
            val phtext = phoneNumber.text.toString().trim();

            // Create an Intent to dial the number
            if (phtext.isNotEmpty()) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val callIntent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:$phtext")
                    }
                    startActivity(callIntent)
                } else {
                    // Request permission
                    requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 101)
                }
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit1 -> {
                Toast.makeText(this, "Context: Edit selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share1 -> {
                Toast.makeText(this, "Context: Share selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}


class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutpage)

    }
}

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.helppage)

    }
}