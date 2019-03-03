
#import "RNTDocBrowserModule.h"
#import <QuickLook/QuickLook.h>

@interface RNTDocBrowserModule()<QLPreviewControllerDataSource, QLPreviewControllerDelegate>

@end

@implementation RNTDocBrowserModule

- (NSInteger)numberOfPreviewItemsInPreviewController:(QLPreviewController *)controller {
    return 1;
}

- (id <QLPreviewItem>)previewController:(QLPreviewController *)controller previewItemAtIndex:(NSInteger)index {
    return [NSURL fileURLWithPath:self.path];
}

- (void)previewControllerDidDismiss:(QLPreviewController *)controller {
    self.controller = nil;
}

RCT_EXPORT_MODULE(RNTDocBrowser);

RCT_EXPORT_METHOD(open:(NSDictionary*)options) {
    
    self.path = [RCTConvert NSString:options[@"path"]];
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
        self.controller = [[QLPreviewController alloc] init];
        self.controller.dataSource = self;
        self.controller.delegate = self;
        
        UIViewController *rootViewController = [UIApplication sharedApplication].keyWindow.rootViewController;
        if (rootViewController != nil) {
            [rootViewController presentViewController:self.controller animated:YES completion:nil];
        }
        
    });
    
}

@end
