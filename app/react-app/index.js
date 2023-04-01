// Modules to control application life and create native browser window
const { app, BrowserWindow } = require('electron');
const path = require('path');
const config = require(path.join(__dirname + '/config.json'));
const icon = path.join(__dirname + '/public/favicon.ico');

function createWindow() {
  const windowConf = config.window;
  const webPreferences = config.webPreferences;
  webPreferences.preload = path.join(__dirname, 'preload.js');

  // Create the browser window.
  const mainWindow = new BrowserWindow({
    width: 1024,
    height: 576,
    autoHideMenuBar: windowConf.hideMenuBar,
    webPreferences,
    icon: icon,
    show: false
  });

  // Endereço do React
  mainWindow.loadURL('http://localhost:3000');

  // Configurações da janela
  if (windowConf.fullScreen) mainWindow.maximize();

  // Open the DevTools.
  if (webPreferences.devTools) mainWindow.webContents.openDevTools();

  // Aguardar o evento 'ready-to-show' antes de exibir a janela
  mainWindow.once('ready-to-show', () => {
    mainWindow.show();
  });
}

app.whenReady().then(() => {
  createWindow();

  app.on('activate', function () {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  })
});

// Fecha o app
app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') app.quit()
});
