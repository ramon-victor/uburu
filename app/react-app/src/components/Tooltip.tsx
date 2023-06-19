import React from "react";
import { Tooltip as MuiTooltip, IconButton } from "@mui/material";
import { MdOutlineInfo } from 'react-icons/md';
import './styles/Tooltip.css';

interface Props {
    info: string;
};

export const Tooltip = ({ info }: Props): JSX.Element => {
    const componentsProps = {
        tooltip: {
            sx: {
                bgcolor: 'rgba(35, 35, 35, 0.95);',
                fontSize: '12px',
                '& .MuiTooltip-arrow': {
                    color: 'rgba(35, 35, 35, 0.95);',
                }
            }
        }
    };

    return (
        <MuiTooltip className="tooltip" title={info} arrow enterDelay={200} leaveDelay={100} componentsProps={componentsProps}>
            <IconButton>
                <MdOutlineInfo />
            </IconButton>
        </MuiTooltip>
    );
};
