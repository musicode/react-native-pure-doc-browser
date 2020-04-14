
#import "RNTDocBrowser.h"
#import <React/RCTConvert.h>

@implementation RNTDocBrowser

static NSString *ERROR_CODE_FILE_NOT_FOUND = @"1";

static BOOL checkFileExisted(NSString *path, RCTPromiseRejectBlock reject) {
    BOOL existed = [NSFileManager.defaultManager fileExistsAtPath:path];
    if (!existed) {
        reject(ERROR_CODE_FILE_NOT_FOUND, @"file is not found.", nil);
        return false;
    }
    return true;
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

- (NSInteger)numberOfPreviewItemsInPreviewController:(QLPreviewController *)controller {
    return 1;
}

- (id <QLPreviewItem>)previewController:(QLPreviewController *)controller previewItemAtIndex:(NSInteger)index {
    return self.url;
}

- (void)previewControllerDidDismiss:(QLPreviewController *)controller {
    self.controller = nil;
    self.url = nil;
}

- (dispatch_queue_t)methodQueue {
    return dispatch_get_main_queue();
}

- (NSDictionary *)constantsToExport {
    return @{
        @"ERROR_CODE_FILE_NOT_FOUND": ERROR_CODE_FILE_NOT_FOUND,
    };
}

RCT_EXPORT_MODULE(RNTDocBrowser);

RCT_EXPORT_METHOD(open:(NSDictionary*)options
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {

    NSString *path = [RCTConvert NSString:options[@"path"]];
    
    if (!checkFileExisted(path, reject)) {
        return;
    }

    self.url = [NSURL fileURLWithPath:path];

    QLPreviewController *controller = [[QLPreviewController alloc] init];
    self.controller = controller;
    
    controller.dataSource = self;
    controller.delegate = self;

    [RCTPresentedViewController() presentViewController:controller animated:YES completion:nil];
    resolve(@{});

}

@end
