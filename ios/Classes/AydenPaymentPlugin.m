#import "AydenPaymentPlugin.h"
#import <ayden_payment/ayden_payment-Swift.h>

@implementation AydenPaymentPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAydenPaymentPlugin registerWithRegistrar:registrar];
}
@end
