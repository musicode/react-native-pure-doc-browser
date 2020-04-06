
#import <React/RCTBridgeModule.h>
#import <QuickLook/QuickLook.h>

@interface RNTDocBrowser : NSObject <RCTBridgeModule>

@property (nonatomic, strong) QLPreviewController *controller;
@property (nonatomic, copy) NSString *path;

@end
