import { ApplicationConfig, provideZoneChangeDetection, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
// For notification popup /toaster/alert
import { MatSnackBarModule } from '@angular/material/snack-bar'; 
//it is for toaster/
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';


export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
    importProvidersFrom(HttpClientModule), // ✅ Required for HttpClient
    importProvidersFrom(MatSnackBarModule,BrowserAnimationsModule, ToastrModule.forRoot())
    
  ]
};
