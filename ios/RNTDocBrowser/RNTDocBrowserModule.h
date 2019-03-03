
#import <React/RCTViewManager.h>
#import <React/RCTBridgeModule.h>

@interface RNTDocBrowserModule : NSObject <RCTBridgeModule>

@property (nonatomic, copy) NSString *path;

@end
