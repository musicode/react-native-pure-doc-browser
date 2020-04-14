
#import <React/RCTBridgeModule.h>
#import <QuickLook/QuickLook.h>

@interface RNTDocBrowser : NSObject <RCTBridgeModule, QLPreviewControllerDataSource, QLPreviewControllerDelegate>

@property (nonatomic, strong) QLPreviewController *controller;
@property (nonatomic, strong) NSURL *url;

@end
