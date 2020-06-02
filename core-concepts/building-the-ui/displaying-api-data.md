---
description: >-
  API data can be visualised in nearly any way conceivable. Whether it's a list
  (Table Widget), a trend (Chart Widget) or even simple text (Text Widget), it
  can be easily visualised in Appsmith
---

# Displaying API Data

## Selecting The Right Widget

Appsmith provides the following widgets to visualise data from an API.

| **Widget** | **Property** | **Data Type** |
| :--- | :--- | :--- |
| **Table** | Table Data | Array of objects |
| **Chart** | Chart Data | Array of \(x,y\) |
| **Text** | Text | String |
| **Image** | Image | URL / Base64  |

## Connecting the API

If you haven't already created an API, you can follow this guide

{% page-ref page="../apis/" %}

Once your API has been created, you can display its data inside your widget property using **`{{ apiName.data }}` .**  Here **apiName** represents the name of the API you entered in the API creation section and the data attribute represents the response of the API. 

![](../../.gitbook/assets/connect-data2.gif)

{% hint style="warning" %}
Sometimes your API response format might not match the format that your widget requires. How you can transform your data is described below
{% endhint %}

## Transforming API Data

You can use Javascript inside the `{{ }}` to transform API data when binding it to a property. ****An example of this would be an API which had an array of values that are needed to populate a dropdown.

A dropdown needs an array of label and value in it's option field, so in order to connect this data to a dropdown, we will transform the data in the dropdown options property:

```javascript
// API Response
[
    {
        "id": "1",
        "name": "Watermelon"
    },
    {
        "id": "2",
        "name": "Apple"
    },
    {
        "id": "3",
        "name": "Grape"
    },
    {
        "id": "4",
        "name": "Bananna"
    },
    {
        "id": "5",
        "name": "Orange"
    },
    {
        "id": "6",
        "name": "Mango"
    }
]
```

```javascript
{{
  fetchFruits.data.map((fruit) => { 
    return { label: fruit.name, value: fruit.id } 
    })
}}

// fetchFruits is the name of the API
```



