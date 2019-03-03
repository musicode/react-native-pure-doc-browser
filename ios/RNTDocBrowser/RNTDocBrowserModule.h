
#import <React/RCTViewManager.h>
#import <React/RCTBridgeModule.h>
#import <QuickLook/QuickLook.h>

@interface RNTDocBrowserModule : NSObject <RCTBridgeModule>

@property (nonatomic, strong) QLPreviewController *controller;
@property (nonatomic, copy) NSString *path;

@end
