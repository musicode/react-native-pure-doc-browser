
#import "RNTDocBrowser.h"
#import <React/RCTConvert.h>
#import <QuickLook/QuickLook.h>

@interface RNTDocBrowser()<QLPreviewControllerDataSource, QLPreviewControllerDelegate>

@end

@implementation RNTDocBrowser

- (NSInteger)numberOfPreviewItemsInPreviewController:(QLPreviewController *)controller {
    return 1;
}

- (id <QLPreviewItem>)previewController:(QLPreviewController *)controller previewItemAtIndex:(NSInteger)index {
    return [NSURL fileURLWithPath:self.path];
}

- (void)previewControllerDidDismiss:(QLPreviewController *)controller {
    self.controller = nil;
}

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(RNTDocBrowser);

RCT_EXPORT_METHOD(open:(NSDictionary*)options
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
    
    self.path = [RCTConvert NSString:options[@"path"]];
    

    self.controller = [[QLPreviewController alloc] init];
    self.controller.dataSource = self;
    self.controller.delegate = self;
    
    UIViewController *rootViewController = [UIApplication sharedApplication].keyWindow.rootViewController;
    if (rootViewController != nil) {
        [rootViewController presentViewController:self.controller animated:YES completion:nil];
        resolve(@[]);
    }
    else {
        reject(@"1", @"rootViewController is nil", nil);
    }
    
}

@end
