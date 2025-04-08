@echo off
@REM 保证变量在循环内正确更新
setlocal enabledelayedexpansion
set cnt=1
for %%i in (%1) do (
    rename %%i !cnt!.jpg
    set /a cnt=!cnt! + 1
)
echo Done!
pause > nul