
#import "BlogListViewController.h"
#import "NSObject+iPhonical.h"
#import "IPContentProvider.h"
#import "BlogDetailsViewController.h"
 

@implementation BlogListViewController




- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	if(section == 0) {
		return [[[contentProvider safeValueForKeyPath:@"content"] asArray] count];
	} else
		return 0;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {


	if(indexPath.section == 0) {
	id item = [[[contentProvider safeValueForKeyPath:@"content"] asArray] objectAtIndex: indexPath.row];
	
    UITableViewCell *cell = [self cellSubtitleForTableView:tableView];
	cell.textLabel.text = [item safeValueForKeyPath:@"creator"];
	cell.detailTextLabel.text = [item safeValueForKeyPath:@"title"];
	
	return cell;


	} else
		return nil;
}


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {


	if(indexPath.section == 0) {
	
	id item = [[[contentProvider safeValueForKeyPath:@"content"]asArray] objectAtIndex: indexPath.row];
		IPContentProvider *provider = [IPSimpleContentProvider providerWithContent:item andProviders:self.contentProvider.providers];
		BlogDetailsViewController *controller = [[BlogDetailsViewController alloc] init];
		controller.contentProvider = provider;
		[self.navigationController pushViewController: controller animated: TRUE];
		[controller release];

	
	} else
		return;
}

-(void)updateData {
	self.navigationItem.title = @"Blog";
	
	[super updateData];
}

@end
