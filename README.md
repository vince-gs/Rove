# Rove
Please don't upload solutions publicly!

## Prototype Compass App “Rove”

Rove is (or at least will be, someday) an app to guide users to particular Destinations, without necessarily revealing exactly where they will be going. Once they arrive (within a specified radius), users are presented with a secret message associated with the Destination. Users should not know the coordinates of the Destination before they arrive (unless they are willing to engage in some serious trigonometry and triangulation)
Eventually (beyond the scope of this project), users will be able to send destinations to each other, and thereby share interesting locations with their friends!

## Instructions

* Clone the project from https://github.com/vince-gs/Rove.git 
* Implement each of the required tasks below and at least one of the optional tasks, ideally committing after each.
* When you’re finished, run `git clean -fdX` to remove build artifacts, then archive/compress the repo and send it back to us with your name on it! (You’ll need to upload to drive/dropbox/etc since gmail hates zip files)

## Notes

Don’t worry about possible odd behavior at special coords like Prime Meridian, poles, etc, (although there really shouldn’t be any)

Any known bugs or odd behaviour are marked. No hidden bugs or gotchas (that we know of!)

No existing classes in the util package should need to be modified

While this is (hypothetically) a prototype app, and you aren’t expected to implement any advanced architectural patterns like MVC, etc, it should still be coded with an eye towards maintainability and extensibility.

## Required Tasks - Make the app functional!

### Change app to launch into PrimerActivity by default

Make the necessary changes such that users start in PrimerActivity instead of MainActivity. “Go” button should launch MainActivity (with arbitrary params for now)

### Show permissions request and location settings dialog in PrimerActivity

In PrimerActivity, request location permissions if we don’t have them, and prompt the user to change their location settings to a higher accuracy if necessary

## Fetch list of destinations from API

Fetch list of destinations from http://demo9392195.mockable.io/destinations, and use the first item in the list to launch MainActivity.

“Go” button should be disabled, and the text should display a loading message, until the API results are available. Highly recommend using Square’s Retrofit library to make the API call
Error behavior is unspecified.

### Show message when user arrives at destination

Create a new activity that shows a destination’s message and a “Done” button, and launch that activity when the user is within [radius] meters of the destination. App should return to PrimerActivity when the user presses “Done”. (Precise layout design of new activity is up to you)

### Make compass arrow point towards destination

Update compass arrow to rotate in response to device orientation*, such that the arrow actually points toward the destination, instead of just showing the bearing relative to North. Recommend using the provided Compass.java to get device orientation in a usable fashion.
(*Compass.java should return a correct bearing that accounts for all 3 axes of rotation, but you needn’t worry about behaviour in cases where the device is e.g. upside down, or completely vertical)

## Optional Tasks (Choose at least one)

### Persist Destination

Persist the current destination, such that the app automatically resumes navigating to it when next started, until and unless the user arrives at the destination or presses cancel

### Choose between Destinations

Give the user a way to choose which destination they want to navigate to, before starting MainActivity. Don’t reveal the coordinates or message, but otherwise the design and implementation is up to you

### Set up a Deep Link with a Destination

Instead of reaching out to the API endpoint, it is possible that a friend could send you a location to go to, or the fine people behind Rove want everyone to go to a certain location. A possible way of doing that would be sending a URL to the user and allowing the application to handle it as a deep link. Set up the app to listen for a deep link with a Destination. The deep link URL can be structured however you choose.

# Destination object structure
```
[
    {
        "id": 1,
        "title": "HQ",
        "latitude": 47.648967,
        "longitude": -122.348117,
        "radius": 10,
        "message": "Say hi at the front desk, then log the cache!"
    },
    {
        "id": 2,
        "title": "Park",
        "latitude": 47.661130,
        "longitude": -122.356181,
        "radius": 20,
        "message": "You made it! Now walk straight to the back :)"
    }
]
```
