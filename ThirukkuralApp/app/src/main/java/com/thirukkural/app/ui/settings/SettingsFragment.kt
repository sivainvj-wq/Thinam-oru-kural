package com.thirukkural.app.ui.settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.thirukkural.app.R
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.databinding.FragmentSettingsBinding
import com.thirukkural.app.notifications.NotificationHelper
import com.thirukkural.app.utils.NotificationScheduler
import com.thirukkural.app.utils.PreferencesManager
import kotlinx.coroutines.launch
import java.util.Locale

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = PreferencesManager(requireContext())
        loadSettings()
        setupListeners()
    }

    private fun loadSettings() {
        binding.switchLanguage.isChecked = prefs.getLanguage() == "ta"
        updateLanguageLabel()
        binding.switchNotification.isChecked = prefs.isNotificationEnabled()
        updateNotificationTime()
        updateNotificationUI()
    }

    private fun setupListeners() {
        binding.switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            prefs.setLanguage(if (isChecked) "ta" else "en")
            updateLanguageLabel()
            showSnackbar(getString(R.string.language_changed))
        }

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            prefs.setNotificationEnabled(isChecked)
            updateNotificationUI()
            if (isChecked) {
                NotificationScheduler.scheduleDaily(requireContext())
                showSnackbar(getString(R.string.notification_enabled))
            } else {
                NotificationScheduler.cancel(requireContext())
                showSnackbar(getString(R.string.notification_disabled))
            }
        }

        binding.layoutNotificationTime.setOnClickListener {
            if (prefs.isNotificationEnabled()) showTimePicker()
        }

        binding.btnTestNotification.setOnClickListener {
            sendTestNotification()
        }
    }

    private fun showTimePicker() {
        val hour = prefs.getNotificationHour()
        val minute = prefs.getNotificationMinute()
        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            prefs.setNotificationTime(selectedHour, selectedMinute)
            updateNotificationTime()
            if (prefs.isNotificationEnabled()) {
                NotificationScheduler.scheduleDaily(requireContext())
                showSnackbar(getString(R.string.notification_rescheduled))
            }
        }, hour, minute, false).show()
    }

    private fun updateNotificationTime() {
        val hour = prefs.getNotificationHour()
        val minute = prefs.getNotificationMinute()
        val amPm = if (hour < 12) "AM" else "PM"
        val displayHour = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }
        binding.tvNotificationTime.text =
            String.format(Locale.getDefault(), "%d:%02d %s", displayHour, minute, amPm)
    }

    private fun updateNotificationUI() {
        val enabled = binding.switchNotification.isChecked
        binding.layoutNotificationTime.isEnabled = enabled
        binding.layoutNotificationTime.alpha = if (enabled) 1f else 0.4f
        binding.btnTestNotification.isEnabled = enabled
        binding.btnTestNotification.alpha = if (enabled) 1f else 0.4f
    }

    private fun updateLanguageLabel() {
        val isTamil = binding.switchLanguage.isChecked
        binding.tvLanguageValue.text = if (isTamil) "தமிழ்" else "English"
    }

    private fun sendTestNotification() {
        showSnackbar(getString(R.string.test_notification_sent))
        Handler(Looper.getMainLooper()).postDelayed({
            val repo = KuralRepository(requireContext())
            val kuralNum = prefs.getTodayKuralNumber()
            viewLifecycleOwner.lifecycleScope.launch {
                val kural = repo.getKuralByNumber(kuralNum)
                kural?.let {
                    NotificationHelper.showKuralNotification(
                        requireContext(), it.number, it.line1, it.line2
                    )
                }
            }
        }, 2000)
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
