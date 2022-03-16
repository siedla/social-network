import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DisplayUsersComponent } from './display-users/display-users.component';
import { DisplayPostsComponent } from './display-posts/display-posts.component';
import { PostAddingFormComponent } from './post-adding-form/post-adding-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SearchComponent } from './search/search.component';
import { DisplayFoundUsersComponent } from './search/display-found-users/display-found-users.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UserViewComponent } from './user-view/user-view.component';
import { BasicAuthHtppInterceptorService } from './services/basic-auth-htpp-interceptor.service';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DisplayFriendsComponent } from './display-friends/display-friends.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { NbThemeModule, NbChatModule } from '@nebular/theme';
import { NbSidebarModule} from '@nebular/theme';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {  NbLayoutModule } from '@nebular/theme';
import { ChatComponent } from './user-view/chat/chat.component';
@NgModule({
  declarations: [
    AppComponent,
    DisplayUsersComponent,
    DisplayPostsComponent,
    PostAddingFormComponent,
    SearchComponent,
    DisplayFoundUsersComponent,
    SignUpComponent,
    SignInComponent,
    UserViewComponent,
    NavBarComponent,
    DisplayFriendsComponent,
    SidebarComponent,
    UserProfileComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NbThemeModule.forRoot(),
    NbChatModule,
    NbSidebarModule.forRoot(),
    NbLayoutModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
