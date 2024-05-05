# Eye-safe 20/20/20 Linux application.

* Sends the notification every 20 mins.
* Plays the random sound located in `${user.home}/.eyesight/sounds` location.

![eyesight-showcase](https://github.com/Dreaght/EyesightNotify/assets/111290888/3101b0f1-f116-4d75-80b5-e3c50e784296)

## Sounds (optional)
#### Add sound files (`.wav`) into `${user.home}/.eyesight/sounds/` folder.
> [!WARNING]
> Currently only `.wav` sound files are supported 
> since Java doesn't support `.mp3` for example!

## Configuration
#### Located at: `${user.home}/.eyesight/config.yml`:
```
play-sound: true

# (s - seconds / m - minutes / h - hours)
notification-rate: 20m
notification-message: "Go away for 20 Secs!"
```

> [!NOTE]
> The default program folder location:
> `/home/yourname/.eyesight/`.
> If you run this application using `sudo`, the location will be:
> `/root/.eyesight/`
> 
> I do not recommend to run application using `sudo`!

## Startup

#### Make application run at startup:
`sudo java -jar EyesightNotify-${version}.jar --enable-startup`

#### Remove application from startup:
`sudo java -jar EyesightNotify-${version}.jar --disable-startup`

> [!WARNING]
> `sudo` is required to add this application to startup services.

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
> 
> So, use `sudo` only to run this application with arguments.
