package de.matso.adyen_payment

import android.app.Activity
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity


class AydenPaymentPlugin(private val activity: Activity): MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "ayden_payment")
      channel.setMethodCallHandler(AydenPaymentPlugin(registrar.activity() as Activity))
    }
  }
  var publishableKey: String? = null

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
      "addSource" -> {
        publishableKey?.let {key ->
            val args = Bundle()
            args.putString("publishableKey", publishableKey)
            startActivity(activity.baseContext,Intent(activity.baseContext, MainActivity::class.java),args);
//          StripeDialog.newInstance("Add Source", key).apply {
//
//            show(this@StripePaymentPlugin.activity.supportFragmentManager, "stripe")
//
//            tokenListener = { token ->
//              result.success(token)
//            }
//          }
        }
        if (publishableKey == null) {
          Log.e("AYDEN", "You have to set a publishable key first")
          result.success(false)
        }
      }
      "setPublishableKey" -> publishableKey = call.arguments as String
      else -> result.notImplemented()
    }
  }
}
