package com.nighthawkapps.wallet.shielded.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.nighthawkapps.wallet.shielded.R
import com.nighthawkapps.wallet.shielded.databinding.FragmentSendConfirmBinding
import com.nighthawkapps.wallet.shielded.di.viewmodel.activityViewModel
import com.nighthawkapps.wallet.shielded.ext.goneIf
import com.nighthawkapps.wallet.shielded.ext.onClickNavTo
import com.nighthawkapps.wallet.shielded.feedback.Report
import com.nighthawkapps.wallet.shielded.feedback.Report.Funnel.Send
import com.nighthawkapps.wallet.shielded.feedback.Report.Tap.*
import com.nighthawkapps.wallet.shielded.ui.base.BaseFragment
import com.nighthawkapps.wallet.shielded.sdk.ext.toAbbreviatedAddress
import com.nighthawkapps.wallet.shielded.sdk.ext.convertZatoshiToZecString
import kotlinx.coroutines.launch

class SendConfirmFragment : BaseFragment<FragmentSendConfirmBinding>() {
    override val screen = Report.Screen.SEND_CONFIRM

    val sendViewModel: SendViewModel by activityViewModel()

    override fun inflate(inflater: LayoutInflater): FragmentSendConfirmBinding =
        FragmentSendConfirmBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNext.setOnClickListener {
            onSend().also { tapped(SEND_CONFIRM_NEXT) }
        }
        R.id.action_nav_send_confirm_to_nav_send_memo.let {
            binding.backButtonHitArea.onClickNavTo(it) { tapped(SEND_CONFIRM_BACK) }
            onBackPressNavTo(it) { tapped(SEND_CONFIRM_BACK) }
        }
        mainActivity?.lifecycleScope?.launch {
            binding.textConfirmation.text =
                "Send ${sendViewModel.zatoshiAmount.convertZatoshiToZecString(8)} ZEC to ${sendViewModel?.toAddress.toAbbreviatedAddress()}?"
        }
        sendViewModel.memo.trim().isNotEmpty().let { hasMemo ->
            binding.radioIncludeAddress.isChecked = hasMemo || sendViewModel.includeFromAddress
            binding.radioIncludeAddress.goneIf(!(hasMemo || sendViewModel.includeFromAddress))
        }
    }

    private fun onSend() {
        sendViewModel.funnel(Send.ConfirmPageComplete)
        mainActivity?.safeNavigate(R.id.action_nav_send_confirm_to_send_final)
    }
}