---
description: >-
  The table is used to display rows of data. You can display data from an API in
  a table, trigger an action when a user selects a row and even work with large
  paginated data sets
---

# Table

## Displaying Data in a table

A table takes data in the form of an array of objects. Adding an array of objects to the table data will display each object as a row of data with each key of the object as a column in the table.

You can also display data from an API in a table by binding it in the table data using 

```javascript
{{ apiName.data }}
// apiName.data is the response of the API which is an array.
```

{% hint style="success" %}
If the API response is not an array, you can transform the response or access an inner key which is an array using the dot operator
{% endhint %}

{% page-ref page="../core-concepts/building-the-ui/displaying-api-data.md" %}

## Displaying Paginated Data

Large datasets are paginated by default on the client in a table to ensure only limited information is displayed on the UI. To display large datasets that are paginated on the server we can

* Enable the server side pagination property in the table
* Call the API on page change in the table
* Bind the pageNo property in the API **`{{ Table1.pageNo }}`**

{% hint style="info" %}
Table1 in the above example is the name of the table which is editable in the property pane
{% endhint %}

## Selected Row

While the table is useful to view large lists of data, you may want to display the data of a single row in greater depth such as in a form. To enable this, you can create the UI that represents the data in a single row of a table and bind each element to the selected row of the table. This code snipper illustrates binding a tables image column to an Image widget.

```text
{{ Table1.selectedRow.imageUrl }}
```

## Properties

| Internal Property | Description |
| :--- | :--- |
| **selectedRow** | Contains the data of the row selected by the user. Will be undefined if no row is selected |
| **pageNo** | Contains the current page number that the user is on. Can be used by APIs for pagination |

| Widget Property | Description |
| :--- | :--- |
| **Table Data** | This property lets you edit the data in the table. You can either write an array of objects to display as table rows or you can bind data from an API using the mustache syntax {{ apiName.data }} |
| **Server Side Pagination**  | Enables you to implement pagination by limiting the number of results fetched per API request. Use this property when your table data is being bound to an API.  |
| **Visible** | Controls widget's visibility on the page. When turned off, the widget will not be visible when the app is published    |

| Action | Description |
| :--- | :--- |
| **Column Action** | Adds a new column to the table with a button against each row. The button can be configured to trigger an action on the corresponding data row.  |
| **onRowSelected** | Sets the action to be run when user selects a row in the table. Default supported actions are: Call API, Navigate to Page, Navigate to URL or Show Alert.  |
| **onPageChange** | Sets the action to be run when a table page is changed. Default supported actions are: Call API, Navigate to Page, Navigate to URL or Show Alert. |



## 

