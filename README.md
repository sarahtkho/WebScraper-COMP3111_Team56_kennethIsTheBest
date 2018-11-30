Fung Hing Lun : Feature 1 and 6<br/>
Ho Tsz Kiu : Feature 4 and 5<br/>
Ho Wai Kin : Feature 2 and 3

We scrape items from two selling portals, Craigslist and Preloved.<br/>
Assumptions: 
1. The item prices are compared in USD.
2. Since there may be too many pagination for a search on Craigslist, so the program may go into long waiting time or even not responding.    Therefore, if a search returns more than 3 pagination, only the items from the first four pages would be scraped to avoid long waiting 	time.   
