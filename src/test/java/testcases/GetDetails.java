package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.GetDetailsPage;
import pages.LoginPage;

public class GetDetails extends BaseTest {

	
	GetDetailsPage getdetails = new GetDetailsPage();
	LoginPage login = new LoginPage();
	
	@Test
	public void get_account_name_and_email_TC017() throws InterruptedException
	{
		String name_email = getdetails.get_account_name_and_email();
		System.out.println(name_email);
	}
	
	@Test
	public void get_ordered_items_TC018() throws InterruptedException
	{
		ArrayList<String> orederd_items = getdetails.get_ordered_items();
		for (String element : orederd_items) {
            System.out.println(element);
        }
	}
	
	@Test
	public void get_items_in_wishlist_TC019() throws InterruptedException
	{
		ArrayList<String> wishlist = getdetails.get_items_in_wishlist();
		for (String element : wishlist) {
            System.out.println(element);
        }
	}
	
	@Test
	public void get_items_in_cart_TC020() throws Exception
	{
		ArrayList<String> items_in_cart = getdetails.items_in_cart();
		items_in_cart.forEach(items->System.out.println(items));
	}
	
	@Test
	public void get_womens_deals_TC021() throws Exception
	{
		ArrayList<String> women_deals = getdetails.get_women_deals();
		women_deals.forEach(women->System.out.println(women));
	}
	
	
	@Test
	public void get_mens_deals_TC022() throws Exception
	{
		ArrayList<String> men_deals = getdetails.get_men_deals();
		men_deals.forEach(men->System.out.println(men));
	}
	
	@Test
	public void get_gear_deals_TC023() throws Exception
	{
		ArrayList<String> gear_deals = getdetails.get_gear_deals();
		gear_deals.forEach(number->System.out.println(number));
	}
	
	
	@Test
	public void remove_items_in_cart_TC024() throws InterruptedException
	{
		getdetails.remove_all_items_from_cart();
	}
	
	@Test
	public void get_cart_subtotal_TC025()
	{
		getdetails.get_cart_subtotal();
	}
	
	
}
