import 'dart:async';

import 'package:flutter/services.dart';

class AydenPayment {
  static const MethodChannel _channel = const MethodChannel('ayden_payment');

  // opens the stripe dialog to add a new card
  /// if the source has been successfully added the card token will be returned
  static Future<String> addSource() async {
    final String token = await _channel.invokeMethod('addSource');
    return token;
  }

  static bool _publishableKeySet = false;

  static bool get ready => _publishableKeySet;

  /// set the publishable key that ayden should use
  /// call this once and before you use [addSource]
  static void setPublishableKey(String apiKey) {
    _channel.invokeMethod('setPublishableKey', apiKey);
    _publishableKeySet = true;
  }
}
