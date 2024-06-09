# Eye-safe 20/20/20 Linux application.

* Sends the notification with period you choose _(default: 20 minutes)_.
* Plays the random sound located in `${user.home}/.eyesight/sounds`.
* Launches at startup.

## It's simple and easy to use!

![eyesight-showcase](https://github.com/Dreaght/EyesightNotify/assets/111290888/3101b0f1-f116-4d75-80b5-e3c50e784296)

## Sounds (optional)
#### Add sound files (`.wav`) into `~/.eyesight/.eyesight/sounds/` folder.
> [!WARNING]
> Currently only `.wav` sound files are supported!

## Configuration
#### Located at: `~/.eyesight/.eyesight/config.yml`
* Launch at startup, notification rate, message, etc.

> [!NOTE]
> The default program folder location:
> `~/.eyesight/`.
> If you run this application using `sudo`, the location is:
> `/root/.eyesight/`.
>
> It's not recommended to run application using `sudo`!

## Problems
> [!CAUTION]
> If you run application using `sudo` command
> it may not send any notifications. You can fix it
> by changing the Notification Service at:
>
> `/usr/share/dbus-1/services/org.freedesktop.Notifications.service`
>
> For example, paste this:
> ```
> [D-BUS Service]
> Name=org.freedesktop.Notifications
> Exec=/usr/lib/notification-daemon/notification-daemon
> ```
>
> `sudo apt install -y notification-daemon`
