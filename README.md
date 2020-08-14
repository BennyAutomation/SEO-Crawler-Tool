# SEO Crawler Tool

A friend of mine works as a digital marketer and asked me to make him a tool for his SEO needs.

This tool will ask the user for a url and direct to its XML sitemap. It then grabs the XML off the sitemap and parses it to find the page links within the site.
After creating a list of the links, it will direct to each page and find the content of its title and meta description tags.

Once all the data has been pulled from the site and stored in a nested list, the application will create a Google Sheet to properly display and save the information.

Tools/Libraries Used in this Project:
- Jsoup
- Javax.xml
- Google Sheets API
- Google Oauth
- Maven
