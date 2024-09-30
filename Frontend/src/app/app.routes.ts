import { Routes } from '@angular/router';
import { LayoutComponent } from './PrimeComponents/layout/layout.component';
import { HomeComponent } from './Components/home/home.component';
import { GroupComponent } from './Components/Groups/group/group.component';
import { GroupPageComponent } from './Components/Groups/group-page/group-page.component';

export const routes: Routes = [
    {
        path:'',
        component:LayoutComponent,
        children:[
            {
                path:'home',
                component:HomeComponent
            },
            {
                path:'group',
                component:GroupComponent,
                
            },
            {
                path:'group/groupPage',
                component:GroupPageComponent
            }
        ]
    }
];
