# OatleeCLIENT — Minecraft 1.21.11

Client-side Fabric utility client targeting Minecraft 1.21.11 and Java 21.

## Build

```powershell
$env:JAVA_HOME = 'C:\path\to\jdk-21'
gradle build
```

The verified dependency targets are Fabric API `0.141.1+1.21.11`, Fabric Loader `0.18.1`, and Loom `1.12.0-alpha.39`. The output archive is named `OatleeClient-1.21.11-1.0.0.jar` by Gradle and is emitted under `build/libs`.

## Controls

Right Shift opens the ClickGUI. Left-click toggles modules. Category headers can be dragged. Escape closes the GUI. Settings are stored in `.minecraft/config/oatleeclient.json`.

The BaseFinder language intentionally describes detections as confidence-scored hypotheses. It only operates on client-loaded information; no packet spoofing, anti-cheat bypass, or server-authoritative movement is included.
