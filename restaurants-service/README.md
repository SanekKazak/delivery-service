## Restaurant service

REST API endpoints:

## Restaurant endpoints

####

    GET /api/restaurants - List of restaurants

####

    GET /api/restaurants - Restaurant details

####

    POST /api/restaurants/{restaurantId}/logo - set logo to restaurant


    POST /api/restaurants - Register a restaurant

    
    PUT /api/restaurants - Change a restaurant profile


    DELETE /api/restaurants - Delete a restaurant

####

## Restaurant item endpoints

####

    GET /api/restaurants/items/{restaurantId} - List of items in a restaurant

####

    POST /api/restaurants/items - Register item in a restaurant

    POST /api/restaurants/items/{itemId}/logo - set logo to restaurant item


    DELETE /api/restaurants/items/{itemId} - Delete item in a restaurant


    PUT /api/restaurants/items - Change a item profile

####

## Restaurant item endpoints

####

    GET /api/restaurants/comments/{restaurantId} - List of comments in the restaurant

####

    GET /api/restaurants/comments/{creatorId} - List of comments of the user

####


    POST /api/restaurants/comments/{restaurantId} - Create comment


    DELETE /api/restaurants/comments/{commentId} - Delete comment 

####
