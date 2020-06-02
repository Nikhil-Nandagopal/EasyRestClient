---
description: >-
  APIs can be called when a user interacts with a widget. This can be configured
  in the property pane of the widget
---

# Calling APIs from Widgets

## Configuring the action

The property pane has an action section where all the interactions that a user can perform with a widget are listed. We can configure an action to be taken when the interaction takes place in this section.

To configure the API we want to call when a button is clicked, we can select the API in the onClick dropdown.

![](../../.gitbook/assets/button-action.gif)

## Handling Success / Error

The action section also allows us to configure the action to take once an API returns with a success or an error. Success / Error is determined by the HTTP status code that is returned by the API. We can decide to display a success or an error message by using the Show Alert action.

![](../../.gitbook/assets/success.gif)

